
<@create fields;field>
    name:${field.name}
    width:${field.width}
</@create>

<#macro create fields>
    <#list fields as field>
        <#nested field/>
    </#list>
</#macro>