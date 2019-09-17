package xin.lz1998.cq.retdata;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class VersionInfoData {

    @JSONField(name = "coolq_directory")
    private String coolqDirectory;
    @JSONField(name = "coolq_edition")
    private String coolqEdition;

    @JSONField(name = "plugin_version")
    private String pluginVersion;

    @JSONField(name = "plugin_build_number")
    private long pluginBuildNumber;

    @JSONField(name = "plugin_build_configuration")
    private String plugin_build_configuration;

}
