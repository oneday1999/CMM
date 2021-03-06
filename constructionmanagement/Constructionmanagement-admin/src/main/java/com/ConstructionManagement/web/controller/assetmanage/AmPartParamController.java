package com.ConstructionManagement.web.controller.assetmanage;

import com.ConstructionManagement.common.annotation.Log;
import com.ConstructionManagement.common.core.controller.BaseController;
import com.ConstructionManagement.common.core.domain.AjaxResult;
import com.ConstructionManagement.common.core.page.TableDataInfo;
import com.ConstructionManagement.common.enums.BusinessType;
import com.ConstructionManagement.system.domain.*;
import com.ConstructionManagement.system.service.*;
import com.ConstructionManagement.web.controller.ExportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/asset/partparam")
public class AmPartParamController extends BaseController {
    @Autowired
    private IAmPartParamService iAmPartParamService;
    @Autowired
    private IAmPartParamKitService iamPartParamKitService;
    @Autowired
    private IAmKitParamService ikps;

    /**
     * 获取列表
     */
    @PreAuthorize("@ss.hasPermi('asset:manage:partparam:list')")
    @GetMapping("/list")
    public TableDataInfo list(AmPartParam amPartParam) {
        startPage();
        List<AmPartParam> amPartParams = iAmPartParamService.selectBySelective(amPartParam);
        return getDataTable(amPartParams);
    }

    /**
     * 获取部件的 配件
     */
    @PreAuthorize("@ss.hasPermi('asset:manage:partparam:list')")
    @GetMapping("/kit/{pid}")
    public AjaxResult getlist(@PathVariable Long pid) {
        if (pid <= 0 || pid == null) return AjaxResult.error("该部件不存在配件");
        List<AmPartParamKit> listKits = iamPartParamKitService.selectByPid(pid);
        ;
        return AjaxResult.success(listKits);
    }

    /**
     * 获取配件
     */
    @PreAuthorize("@ss.hasPermi('asset:manage:kitparam:list')")
    @GetMapping("/kits")
    public AjaxResult getKitS(AmPartParam amPartParam) {
        AmKitParam akp = new AmKitParam();
        akp.setApplicableKitType(amPartParam.getPart_type());
        List<AmKitParam> listKits = ikps.selectBySelective(akp);
        ;
        return AjaxResult.success(listKits);
    }

    /**
     * 删除
     */
    @PreAuthorize("@ss.hasPermi('asset:manage:partparam:remove')")
    @Log(title = "部件参数", businessType = BusinessType.DELETE)
    @DeleteMapping("/{Ids}")
    public AjaxResult remove(@PathVariable Long[] Ids) {
        return toAjax(iAmPartParamService.deleteByIds(Ids));
    }

    /**
     * 新增
     */
    //@Transactional()
    @PreAuthorize("@ss.hasPermi('asset:manage:partparam:add')")
    @Log(title = "部件参数", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody AmPartParam amPartParam) {
        if (amPartParam == null)
            return AjaxResult.error("全空部件参数禁止插入");
//        String partCode=amPartParam.getPartCode();
//        String partModel=amPartParam.getPartModel();
//        String partName=amPartParam.getPartName();
        Long pid;
//        if (partCode!=null&&partModel!=null&&null!=partName&&iAmPartParamService.selectByParam
//                (partCode,partModel, partName)!=null) {
//            return AjaxResult.error("该部件参数已存在");
//        }

        int result = iAmPartParamService.insertSelective(amPartParam);
        if (result < 0)
            return AjaxResult.error("添加部件参数失败");
        pid = amPartParam.getId();
        List<AmPartParamKit> kits = amPartParam.getAmPartParamKits();

        if (kits != null && kits.size() > 0 && !kits.isEmpty()) {
            for (AmPartParamKit kit : kits) {
                kit.setPid(pid);
                result *= iamPartParamKitService.insertSelective(kit);

            }
        }
        return toAjax(result);
    }

    /**
     * 修改保存
     */
    @PreAuthorize("@ss.hasPermi('asset:manage:partparam:edit')")
    @Log(title = "部件参数", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody AmPartParam amPartParam) {
        Long pid = amPartParam.getId();
        List<AmPartParamKit> kits = amPartParam.getAmPartParamKits();
        int result = iAmPartParamService.updateByPrimaryKeySelective(amPartParam);
        result *= iamPartParamKitService.deleteByPid(pid);
        for (AmPartParamKit kit : kits) {
            kit.setPid(pid);
            result *= iamPartParamKitService.insertSelective(kit);

        }
        return toAjax(result);
    }

    @Log(title = "部件参数导出", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('asset:manage:partparam:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, AmPartParam wwh) {
        List<AmPartParam> list = iAmPartParamService.selectBySelective(wwh);
        if(list!=null&&list.size() > 0 && !list.isEmpty()){
            for(AmPartParam app:list){
                List<AmPartParamKit> kits = iamPartParamKitService.selectByPid(app.getId());
                app.setAmPartParamKits(kits);
            }
        }
        new ExportUtil().outPut(response, "部件参数表", list, AmPartParam.class);
    }


}
