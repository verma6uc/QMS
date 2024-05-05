<!DOCTYPE html>
<html lang="en" dir="ltr" data-bs-theme="light" data-color-theme="Blue_Theme" data-layout="vertical">

<head>
  <!-- Required meta tags -->
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />

  <!-- Favicon icon-->
  <link rel="shortcut icon" type="image/png" href="/assets/images/logos/favicon.png" />

  <!-- Core Css -->
  <link rel="stylesheet" href="/assets/css/styles.css" />

  <title>Leucine QMS Admin</title>
</head>

<body>
  <!-- Preloader would go here -->
  
  <div id="main-wrapper" class="p-0 bg-white auth-customizer-none">
    <div class="auth-login position-relative overflow-hidden d-flex align-items-center justify-content-center px-7 px-xxl-0 rounded-3 h-n20">
      <div class="auth-login-shape position-relative w-100">
        <div class="auth-login-wrapper card mb-0 container position-relative z-1 h-100 mh-n100">
          <div class="card-body">
            <img src="/assets/images/logos/logo-dark.svg" alt="Leucine QMS Logo" class="mb-4">
            <div class="row align-items-center justify-content-center">
              <div class="col-md-8">
                <h2 class="mb-4 fs-8 fw-bolder text-center">Welcome to Leucine QMS</h2>
                <p class="text-dark fs-4 mb-4 text-center">Your Admin Dashboard</p>
                
                <form action="/auth" method="POST"> <!-- loginServletUrl should be replaced with the actual servlet URL -->
                  <div class="mb-4">
                    <label for="username" class="form-label fw-bold">Username</label>
                    <input type="text" class="form-control py-6" id="username" name="username" required>
                  </div>
                  <div class="mb-4">
                    <label for="password" class="form-label fw-bold">Password</label>
                    <input type="password" class="form-control py-6" id="password" name="password" required>
                  </div>
                  
                  <button type="submit" class="btn btn-primary w-100 mb-3 rounded-pill">Sign In</button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    
  </div>
  <!-- Import Js Files -->
  <script src="/assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
  <script src="/assets/libs/simplebar/dist/simplebar.min.js"></script>
  
</body>

</html>