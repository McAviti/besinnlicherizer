<#-- @ftlvariable name="besinnliche_images" type="kotlin.collections.List<net.mcaviti.besinnlicherizer.model.BesinnlichesImage>" -->
<#import "_layout.ftl" as layout />
<@layout.header>
    <p>
        <a href="/besinnliche_images/new">Create Besinnliches Image</a>
    </p>
    <hr>
    <#list besinnliche_images?reverse as besinnliches_image>
        <div>
                <a href="/besinnliche_images/${besinnliches_image.id}">${besinnliches_image.id}</a>: ${besinnliches_image.srcFilename}
        </div>
    </#list>
    <hr>
</@layout.header>

