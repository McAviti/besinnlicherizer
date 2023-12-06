<#-- @ftlvariable name="besinnliche_images" type="kotlin.collections.List<net.mcaviti.besinnlicherizer.model.BesinnlichesImage>" -->
<#import "_layout.ftl" as layout />
<@layout.header>
    <p>
    <form action=/besinnliche_images/new">
        <button type="submit">Create Besinnliches Image</button>
    </form>

    </p>
    <hr>
    <ul>
    <#list besinnliche_images?reverse as besinnliches_image>
        <li>
                <a href="/besinnliche_images/${besinnliches_image.id}">${besinnliches_image.id}</a>: ${besinnliches_image.srcFilename}
        </li>
    </#list>
    </ul>
    <hr>
</@layout.header>

