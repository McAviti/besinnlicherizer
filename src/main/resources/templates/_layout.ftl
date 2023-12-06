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
            a:visited {
                color: darkgoldenrod;
                background-color: transparent;
                text-decoration: none;
            }
            .button {
                border: none;
                color: gold;
                background-color: darkgoldenrod;
                padding: 16px 32px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                transition-duration: 0.4s;
                cursor: pointer;
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
    <script src="https://unpkg.com/magic-snowflakes/dist/snowflakes.min.js"></script>
    <script>
        var sf = new Snowflakes();
    </script>
    </body>
    </html>
</#macro>