<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link rel="stylesheet" href="./css/login.css">
    <title>Login/Register Form</title>
    <style>
      
    </style>
</head>
<body>
    <div id="logreg-forms">
        <form class="form-signin" action="login" method="post">
            <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Sign in</h1>
            <p class="text-danger">${mess}</p>
            <input name="username" type="text" id="inputUser" class="form-control" placeholder="Username" required autofocus>
            <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
            
            <button class="btn btn-success btn-block" type="submit"><i class="fas fa-sign-in-alt"></i> Sign in</button>
            <hr>
            <button class="btn btn-primary btn-block" type="button" id="btn-signup"><i class="fas fa-user-plus"></i> Sign up New Account</button>
        </form>

        <form action="signup" method="post" class="form-signup" style="display: none;">
            <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Sign up</h1>
            <input name="username" type="text" id="inputUsername" class="form-control" placeholder="Username" required autofocus>
            <input name="password" type="password" id="inputNewPassword" class="form-control" placeholder="Password" required>
            <input name="repassword" type="password" id="username-repeatpass" class="form-control" placeholder="Repeat Password" required>
            <input name="email" type="email" id="inputEmail" class="form-control" placeholder="Email address" required>
            <button class="btn btn-primary btn-block" type="submit"><i class="fas fa-user-plus"></i> Sign up</button>
            <a href="#" id="cancel_signup"><i class="fas fa-angle-left"></i> Back</a>
        </form>

      
        <form action="/reset/password/" class="form-reset" style="display: none;">
            <input type="email" id="resetEmail" class="form-control" placeholder="Email address" required autofocus>
            <button class="btn btn-primary btn-block" type="submit">Reset Password</button>
            <a href="#" id="cancel_reset"><i class="fas fa-angle-left"></i> Back</a>
        </form>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script>
        function toggleResetPswd(e){
            e.preventDefault();
            $('#logreg-forms .form-signin').toggle();
            $('#logreg-forms .form-reset').toggle();
        }

        function toggleSignUp(e){
            e.preventDefault();
            $('#logreg-forms .form-signin').toggle();
            $('#logreg-forms .form-signup').toggle();
        }

        $(function(){
            $('#logreg-forms #forgot_pswd').click(toggleResetPswd);
            $('#logreg-forms #cancel_reset').click(toggleResetPswd);
            $('#logreg-forms #btn-signup').click(toggleSignUp);
            $('#logreg-forms #cancel_signup').click(toggleSignUp);
        });
    </script>
</body>
</html>
