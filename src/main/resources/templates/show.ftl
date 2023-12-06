<#-- @ftlvariable name="besinnliches_image" type="net.mcaviti.besinnlicherizer.model.BesinnlichesImage" -->
<#import "_layout.ftl" as layout />
<@layout.header>
    <div>
        <h3>Create Besinnliches Image</h3>
        <table class="center">
            <tr>
                <th>Your Regular Image.</th>
                <th>The Besinnlicherized Image!</th>
            </tr>
            <tr>
                <td>
                    <#if besinnliches_image.srcFilename == "">
                        <form action="/besinnliche_images/${besinnliches_image.id}/src_img"
                              name="addContentForm" method="post"
                              enctype="multipart/form-data">
                            <input type="file" name="uploadedFile" size="25"
                               id="src_img" />
                            <input type="submit" value="Submit">
                        </form>
                    <#else>
                        <img src="/besinnliche_images/${besinnliches_image.id}/src_img" onclick="this.requestFullscreen()">
                    </#if>
                </td>
                <td>
                    <#if besinnliches_image.besinnlichFilename != "">
                        <img src="/besinnliche_images/${besinnliches_image.id}/besinnliches_img" onclick="this.requestFullscreen()">
                    <#else>
                        <form action="/besinnliche_images/${besinnliches_image.id}/besinnliches_img" method="post" onsubmit="sf.start();">
                            <input type="submit" value="Generate">
                        </form>
                    </#if>
                </td>
            </tr>
            <#if besinnliches_image.prompt != "">
            <tr>
                <td colspan="2"><pre>${besinnliches_image.prompt}</pre></td>
            </tr>
            </#if>
        </table>
    </div>
</@layout.header>