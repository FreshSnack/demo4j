<@createForm fields;field,colspan>
    <th>${field.name}</th>
    <td<#if colspan gt 1> colspan="${colspan}"</#if>>${field.width}</td>
</@createForm>

<#--创建form-->
<#macro createForm fields>
    <#local minWidth = 1/><#--最小列宽-->
    <#local curWidth = 1/><#--当前宽度-->
    <#list fields as field>
        <#if (curWidth + field.width > 1)>
            <#local curWidth = field.width/>
        <#else>
            <#local curWidth = curWidth + field.width/>
        </#if>
        <#if field_has_next && (curWidth + fields[field_index+1].width <= 1)>
            <#if field.width < minWidth>
                <#local minWidth = field.width/>
            </#if>
        </#if>
    </#list>
    ${LOG("minWidth:" + minWidth)}
    <#local curWidth = 1/><#--当前宽度-->
    <#list fields as field>
        <#if (curWidth + field.width > 1)>
<tr>
            <#local curWidth = field.width/>
        <#else>
            <#local curWidth = curWidth + field.width/>
        </#if>
        <#if field_has_next && (curWidth + fields[field_index+1].width <= 1)>
            <#nested field field.width/minWidth-1/>
        <#elseif !field_has_next && curWidth == 1>
            <#nested field field.width/minWidth-1/>
        <#else>
            <#nested field 2/minWidth-1/>
        </#if>
        <#if !field_has_next || (curWidth + fields[field_index+1].width > 1)>
</tr>
        </#if>
    </#list>
</#macro>