
<#include "base.ftl"/>

<#macro print message>
    hello!!, ${message}
</#macro>

<#macro print message>
    hello, ${message}
</#macro>

This is a content!
<@print "abc"/>