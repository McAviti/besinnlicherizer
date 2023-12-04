<#macro header>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <title>Besinnlicherizer</title>
        <style>
            table, th, td {
                border: 1px solid gold;
                padding: 10px;
            }
            .center {
                margin-left: auto;
                margin-right: auto;
            }
            img {
                max-width: 500px;
                max-height: 500px;
            }
        </style>
    </head>
    <body style="text-align: center; color: gold; font-family: sans-serif; background-color:black;">
    <img src="/static/the_besinnlicherizer.png">
    <h1>The Besinnlicherizer: Create Besinnliche Images </h1>
    <p><i>Powered by The Guvnor's Post Men</i></p>
    <hr>
    <#nested>
    <a href="/">Back to the main page</a>
    </body>
    </html>
</#macro>