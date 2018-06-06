<!doctype html>
<html lang="{{ app()->getLocale() }}">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Monitoring - Denemarken</title>

        <!-- Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Raleway:100,600" rel="stylesheet" type="text/css">

        <!-- bootstrap -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

        <!-- jQuery import -->
        <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>

        <!-- Font awesome import -->
        <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container" style="margin: auto auto">
            <table class="table table-striped table-bordered table-dark table-hover">
                <thead>
                    <tr>
                        <td width="55%"><strong>Domein</strong></td>
                        <td width="15%"><strong>Naam</strong></td>
                        <td width="15%"><strong>Status</strong></td>
                        <td width="15%"><strong>Tijd</strong></td>
                    </tr>
                </thead>
                <tbody id="tableBody">
                    {{-- JavaScript responsible for content --}}
                </tbody>
            </table>
        </div>
    </body>

    <script>
        let endpoints = [
            ['http://192.168.25.135:8080/', 'home'],
            ['http://192.168.25.135:9996/verplaatsingsmodule', 'nothome']
        ];

        $(document).ready(function () {
            endpoints.forEach(function (endpoint) {
                $('#tableBody').append('<tr><td>' + endpoint[0] + '</td>' +
                    '<td>' + endpoint[1] + '</td>' +
                    '<td style="text-align: center"><i class="fa fa-spinner fa-spin ' + endpoint[1] + '"></i></td>' +
                    '<td id="' + endpoint[1] +'" style="text-align: center"></td></tr>');
                
                checkUptime(endpoint);
            });

        });

        function checkUptime(url) {
            let selector = '.' + url[1];
            let ajaxTime = new Date().getTime();

            $.ajax({
                url: url[0],
                type: "GET",
                success: function (response) {
                    console.log(response.status);
                    displayLoadTime('#' + url[1], new Date().getTime() - ajaxTime);
                    displaySuccess(selector);
                },
                error: function (request, status, error) {
                    console.log(request);
                    console.log(status);
                    console.log(error);

                    displayLoadTime('#' + url[1], new Date().getTime() - ajaxTime);

                    let statusCode = 403;

                    if(statusCode != 404) {
                        displaySuccess(selector);
                    } else {
                        displayFailure(selector);
                    }
                }
            });
        }

        function displaySuccess(selector) {
            $(selector).removeClass('fa-spinner');
            $(selector).removeClass('fa-spin');
            $(selector).removeClass('fa-times');
            $(selector).addClass('fa-check');
            $(selector).css('color', 'green');
        }

        function displayFailure(selector) {
            $(selector).removeClass('fa-spinner');
            $(selector).removeClass('fa-spin');
            $(selector).removeClass('fa-check');
            $(selector).addClass('fa-times');
            $(selector).css('color', 'red');
        }

        function displayLoadTime(id, responseTime) {
            $(id).append(responseTime + 'ms');
        }
    </script>
</html>
