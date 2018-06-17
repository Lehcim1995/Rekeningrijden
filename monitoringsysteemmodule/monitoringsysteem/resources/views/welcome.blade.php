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

        <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
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
            ['http://192.168.25.135:8000', 'Politie'],
            ['http://i310866.hera.fhict.nl/', 'Portal'],
            ['http://i310866.hera.fhict.nl/pages/simulation/simulatie.html', 'Simulatie'],
            ['http://192.168.25.135:8080/rekeningadministratiemodule/', 'Administratie'],
            ['http://192.168.25.135:8090', 'Rekeningrijder'],
            ['http://192.168.25.130:8000', 'Splunk'],
            ['http://192.168.25.135:9000', 'Sonarqube'],
            ['http://192.168.25.135:8080', 'Jenkins'],
            ['http://192.168.25.135:8081', 'Artifactory']
        ];

        $(document).ready(function () {
            endpoints.forEach(function (endpoint) {
                $('#tableBody').append('<tr><td><a href="' + endpoint[0] + '">' + endpoint[0] + '</a></td>' +
                    '<td>' + endpoint[1] + '</td>' +
                    '<td style="text-align: center"><i class="fa fa-spinner fa-spin ' + endpoint[1] + '"></i></td>' +
                    '<td id="' + endpoint[1] +'" style="text-align: center"></td></tr>');

                checkUptime(endpoint);
            });

        });

        function checkUptime(url) {
            let selector = '.' + url[1];
            let ajaxTime = new Date().getTime();

            axios.get(url[0])
                .then(function (response) {
                    displayLoadTime('#' + url[1], new Date().getTime() - ajaxTime);

                    console.log(response);
                    response.status !== 404 ? displaySuccess(selector) : displayFailure(selector);
                })
                .catch(function (error) {
                    displayLoadTime('#' + url[1], new Date().getTime() - ajaxTime);
//                    error.response.status !== 404 ? displaySuccess(selector) : displayFailure(selector);
                    if(error.response === undefined) {
                        displaySuccess(selector);
                    } else if(error.response.status === 404) {
                        displayFailure(selector)
                    } else {
                        displaySuccess(selector);
                    }
//                    console.log(error.request);
//                    return Promise.reject(error.response);
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

        function displayLoading(selector) {
            $(selector).removeClass('fa-check');
            $(selector).removeClass('fa-times');
            $(selector).addClass('fa-spinner');
            $(selector).addClass('fa-spin');
            $(selector).css('color', 'white');
        }

        function displayLoadTime(id, responseTime) {
            $(id).html(responseTime + 'ms');
        }
    </script>
</html>
