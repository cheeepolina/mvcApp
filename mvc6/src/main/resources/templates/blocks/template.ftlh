<#macro template user>
    <!doctype html>
    <html lang="ru">
    <head>
    <link rel="stylesheet" href="/static/images/m.ico">
    <link rel="shortcut icon" href="C:\Users\trehl\OneDrive\Рабочий стол\m.ico" type="image/x-icon">
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="/static/css/style.css">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
              integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
              crossorigin="anonymous">

                <title>THE COLLECTOR'S DEN</title>
    </head>
    <body>
    <nav class="navbar" style="background-color: #FCE883;">
        <div class="container-fluid">
            <a href="/">

                <span class="navbar-brand mb-0 h1" style="font-family:Impact; color:black;">THE COLLECTOR'S DEN</span>
            </a>
            <#if user.email??>
                <div class="dropdown text-end">
                    <a href="/profile">
                        <img src="/parts/images/avatar.png" alt="mdo"
                             width="32" height="32" class="rounded-circle">
                    </a>
                </div>
            <#else>
               <button type="button" class="btn btn-outline-dark"
                onclick="window.location.href = '/login'">
                    Войти или зарегистрироваться
                </button>
            </#if>
        </div>
    </nav>
    <div class="container mt-4">
        <#nested/>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
            integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
            crossorigin="anonymous"></script>
    </body>
    </html>
</#macro>