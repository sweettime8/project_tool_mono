<template>
  <div>
    <div class="account-pages my-5 pt-5">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-md-8 col-lg-6 col-xl-5">
            <div class="card overflow-hidden">
              <div class="bg-soft bg-primary login-title">
                <div class="row">
                  <div class="col-7">
                    <div class="text-primary p-4" v-if="isShowFormLogin">
                      <h5 class="text-primary">Welcome Back!</h5>
                      <p>Sign in to continue to Devstore.</p>
                    </div>
                    <div class="text-primary p-4" v-if="isShowFormRegister">
                      <h5 class="text-primary">Register</h5>
                      <p>Get your Devstore account now.</p>
                    </div>
                    <div
                      class="text-primary p-4"
                      v-if="isShowFormForgotPassword"
                    >
                      <h5 class="text-primary">Reset Password</h5>
                      <p>Re-Password with DevStore.</p>
                    </div>
                  </div>
                  <div class="col-5 align-self-end">
                    <img
                      src="http://skote.vuejs-light.themesbrand.com/img/profile-img.ba4e037e.png"
                      alt=""
                      class="img-fluid"
                    />
                  </div>
                </div>
              </div>
              <div class="card-body pt-0">
                <!-- form login -->
                <b-form class="p-2" v-if="isShowFormLogin">
                  <b-form-group
                    id="input-group-1"
                    label="Username"
                    label-for="input-1"
                    class="mt-4"
                  >
                    <b-form-input
                      id="input-1"
                      type="text"
                      placeholder="Enter username"
                      v-model="$v.userInfoLogin.username.$model"
                      :state="validateStateLogin('username')"
                      aria-describedby="username-live-feedback"
                    ></b-form-input>
                    <b-form-invalid-feedback id="username-live-feedback">Username is required</b-form-invalid-feedback>
                  </b-form-group>

                  <b-form-group
                    id="input-group-2"
                    label="Password"
                    label-for="input-2"
                    class="mt-4"
                  >
                    <b-form-input
                      id="input-2"
                      :type="typePassword"
                      placeholder="Enter password"
                      v-model="$v.userInfoLogin.password.$model"
                      :state="validateStateLogin('password')"
                      aria-describedby="password-live-feedback"
                    ></b-form-input>
                    <b-form-invalid-feedback id="password-live-feedback">
                        <span v-if="!$v.userInfoLogin.password.required">Password is required</span>
                        <span v-if="!$v.userInfoLogin.password.minLength">You must be at least 6 characters</span>
                        <!-- <span v-if="$v.userInfoLogin.password.minLength && !$v.userInfoLogin.password.isUnique"> You must be at least one uppercase letter, one lowercase letter, one number and one special character</span> -->
                    </b-form-invalid-feedback>
                  </b-form-group>

                  <div class="form-check custom-control custom-checkbox mt-4">
                    <input
                      id="customControlInline"
                      type="checkbox"
                      name="checkbox-1"
                      class="custom-control-input"
                      value="accepted"
                      @click="toggleShowPassword"
                      v-model="showPassword"
                    /><label
                      for="customControlInline"
                      class="custom-control-label"
                    >
                      Show password
                    </label>
                  </div>

                  <b-button variant="primary" class="mt-4" style="width: 100%" @click="handleLogin">Login</b-button>

                  <div class="mt-4 text-center">
                    <a
                      href="javascript:void(0)"
                      class="text-muted"
                      @click="handleForgotPassword"
                      ><i class="mdi mdi-lock me-1"></i> Forgot your password?
                    </a>
                  </div>
                </b-form>
                <!-- form register -->
                <b-form class="p-2" v-if="isShowFormRegister">
                  <b-form-group class="mb-3 mt-4" label-for="txtFullName" label="Fullname">
                    <b-form-input
                      id="txtFullName"
                      type="text"
                      v-model="$v.userInfo.fullname.$model"
                      :state="validateStateRegister('fullname')"
                      aria-describedby="fullname-live-feedback"
                      placeholder="Enter full name"
                    ></b-form-input>
                    <b-form-invalid-feedback id="fullname-live-feedback">Full Name is required
                    </b-form-invalid-feedback>
                  </b-form-group>

                  <b-form-group class="mb-3 mt-4" label-for="txtEmail" label="Email">
                    <b-form-input
                      id="txtEmail"
                      type="text"
                      v-model="$v.userInfo.email.$model"
                      :state="validateStateRegister('email')"
                      aria-describedby="email-live-feedback"
                      placeholder="Enter email"
                    ></b-form-input>
                    <b-form-invalid-feedback id="email-live-feedback">
                        <span v-if="!$v.userInfo.email.required">Email is required</span>
                        <span v-if="!$v.userInfo.email.email">You must specify a valid email address</span>
                    </b-form-invalid-feedback>
                  </b-form-group>

                  <b-form-group class="mb-3 mt-4" label-for="txtUserName" label="Username">
                    <b-form-input
                      id="txtUserName"
                      type="text"
                      v-model="$v.userInfo.username.$model"
                      :state="validateStateRegister('username')"
                      aria-describedby="username-live-feedback"
                      placeholder="Enter username"
                    ></b-form-input>
                    <b-form-invalid-feedback id="username-live-feedback">
                        <span v-if="!$v.userInfo.username.required">Username is required</span>
                    </b-form-invalid-feedback>
                  </b-form-group>
                  <b-form-group class="mb-3 mt-4" label-for="txtPassword" label="Password">
                    <b-form-input
                      id="txtPassword"
                      :type="typePassword"
                      v-model="$v.userInfo.password.$model"
                      :state="validateStateRegister('password')"
                      aria-describedby="password-live-feedback"
                      placeholder="Enter password"
                    ></b-form-input>
                    <b-form-invalid-feedback id="password-live-feedback">
                        <span v-if="!$v.userInfo.password.required">Password is required</span>
                        <span v-if="!$v.userInfo.password.minLength">You must be at least 6 characters</span>
                        <span v-if="$v.userInfo.password.minLength && !$v.userInfo.password.isUnique"> You must be at least one uppercase letter, one lowercase letter, one number and one special character</span>
                    </b-form-invalid-feedback>
                  </b-form-group>
                  <b-form-group
                    class="mb-3 mt-4"
                    label-for="txtConfirmPassword"
                    label="Confirm password"
                  >
                    <b-form-input
                      id="txtConfirmPassword"
                      :type="typePassword"
                      v-model="$v.userInfo.confirmPassword.$model"
                      :state="validateStateRegister('confirmPassword')"
                      aria-describedby="confirmPassword-live-feedback"
                      placeholder="Enter confirm password"
                    ></b-form-input>
                    <b-form-invalid-feedback id="confirmPassword-live-feedback">
                        <span v-if="!$v.userInfo.confirmPassword.required">Password is required</span>
                        <span v-if="!$v.userInfo.confirmPassword.sameAsPassword && $v.userInfo.confirmPassword.required">Password must be identical</span>
                    </b-form-invalid-feedback>
                  </b-form-group>
                    <div class="form-check custom-control custom-checkbox mt-4">
                    <input
                      id="customControlInline"
                      type="checkbox"
                      name="checkbox-1"
                      class="custom-control-input"
                      value="accepted"
                      @click="toggleShowPassword"
                      v-model="showPassword"
                    /><label
                      for="customControlInline"
                      class="custom-control-label"
                    >
                      Show password
                    </label>
                  </div>
                  <b-button
                    variant="primary"
                    class="mt-4"
                    style="width: 100%"
                    @click="submitUser"
                    >Register</b-button
                  >
                  <div class="mt-4 text-center">
                    By registering you agree to the Devstore<a
                      href="javascript:void(0)"
                    >
                      Terms of Use</a
                    >
                  </div>
                </b-form>
                <!-- form forgot password -->
                <b-form class="p-2" v-if="isShowFormForgotPassword">
                  <b-form-group
                    class="mb-3 mt-4"
                    label-for="txtEmail"
                    label="Email"
                  >
                    <b-form-input
                      id="txtEmail"
                      type="text"
                      v-model="userInfo.email"
                      placeholder="Email"
                    ></b-form-input>
                  </b-form-group>

                  <b-button variant="primary" class="mt-4" style="width: 100%"
                    >Reset</b-button
                  >
                </b-form>
              </div>
            </div>
            <div class="mt-5 text-center">
              <p v-if="isShowFormLogin">
                Don't have an account ?
                <a
                  href="javascript:void(0)"
                  class="fw-medium text-primary"
                  @click="handleDisplayFormRegister"
                  >Register now</a
                >
              </p>
              <p v-if="isShowFormRegister">
                Already have an account ?
                <a
                  href="javascript:void(0)"
                  class="fw-medium text-primary"
                  @click="handleDisplayFormLogin"
                  >Login</a
                >
              </p>
              <p v-if="isShowFormForgotPassword">
                Remember It ?
                <a
                  href="javascript:void(0)"
                  class="fw-medium text-primary"
                  @click="handleDisplayFormLogin"
                  >Sign In here</a
                >
              </p>
              <p>© 2022 DevStore <i class="mdi mdi-heart text-danger"></i></p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import UserManageRepository from "../../../repo/UserManageRepository";
// import LoginRepository from "../../../repo/LoginRepository";
import Swal from "sweetalert2";
import * as CONFIG from "@/config/index";
import axios from "axios";
import { required, minLength, email, sameAs} from "vuelidate/lib/validators";
export default {
    name: "Login",
    page: {
        title: "Login",
        meta: [
            {
                name: "description",
            },
        ],
    },
    data() {
        return {
            isShowFormRegister: false,
            isShowFormForgotPassword: false,
            isShowFormLogin: true,
            showPassword:false,
            typePassword:'password',
            userInfo: {
                fullname: '',
                email: '',
                username: '',
                password: '',
                confirmPassword: '',
                newPassword: '',
                active: true
            },
            searchForm: {
                username: '',
                includeActive: true,
                includelnActive: true,
                maxResults: 0,
                startAt: 0
            },
            userInfoLogin: {
                username: '',
                password: ''
            },
            userTitle: '',
            listUser: [],
            event: {
                title: "",
                category: "",
            },
        }
    },
    validations: {
        userInfoLogin: {
            username: {
                required,
            },
            password: {
                required,
                minLength: minLength(6),
                // isUnique(value){
                //     if(value === '') return true;
                //     var password_regex = /^(?=.*[A-Z]{1,})(?=.*[a-z]{1,})(?=.*[0-9]{1,})(?=.*[#?!@$%^&*-]{1,}).{8,}$/
                //     return new Promise((resolve) => {
                //         setTimeout(()=>{
                //             resolve(password_regex.test(value));
                //         },1000);
                //     })
                // }
            }
        },
        userInfo: {
            fullname: {
                required
            },
            email: {
                required,
                email
            },
            username: {
                required
            },
            password: {
                required,
                minLength: minLength(8),
                isUnique(value){
                    if(value === '') return true;
                    var password_regex = /^(?=.*[A-Z]{1,})(?=.*[a-z]{1,})(?=.*[0-9]{1,})(?=.*[#?!@$%^&*-]{1,}).{8,}$/
                    return new Promise((resolve) => {
                        setTimeout(()=>{
                            resolve(password_regex.test(value));
                        },1000);
                    })
                }
            },
            confirmPassword: {
                required,
                sameAsPassword: sameAs('password')
            }
        }
    },
    methods: {
        toggleShowPassword(){
            if(this.showPassword === true){
                this.typePassword = "password";
            }else{
                this.typePassword = "text";
            }
        },
        validateStateLogin(name) {
            const { $dirty, $error } = this.$v.userInfoLogin[name];
            return $dirty ? !$error : null;
        },
        validateStateRegister(name) {
            const { $dirty, $error } = this.$v.userInfo[name];
            return $dirty ? !$error : null;
        },
        handleDisplayFormRegister() {
            this.isShowFormRegister = true;
            this.isShowFormLogin = false;
            this.isShowFormForgotPassword = false;
            this.userInfo.fullname = '';
            this.userInfo.email = '';
            this.userInfo.username = '';
            this.userInfo.password = '';
            this.userInfo.confirmPassword = '';
        },
        handleDisplayFormLogin() {
            this.isShowFormLogin = true;
            this.isShowFormRegister = false;
            this.isShowFormForgotPassword = false;
            this.userInfoLogin.username = '';
            this.userInfoLogin.password = '';
        },
        handleForgotPassword() {
            this.isShowFormForgotPassword = true;
            this.isShowFormLogin = false;
            this.isShowFormRegister = false;
        },
        submitUser: async function () {
            this.$v.userInfo.$touch();
            // if (this.$v.userInfo.$anyError === false) {
            //     await UserManageRepository.create(this.userInfo).then((response) => {
            //         if (response) {
            //             Swal.fire(
            //                 'Good job!',
            //                 'Data create Success!',
            //                 'success'
            //             );
            //             this.isShowFormRegister = false;
            //             this.isShowFormLogin = true;
            //         } else {
            //             Swal.fire({
            //                 icon: 'error',
            //                 title: 'Oops...',
            //                 text: 'response.message[0].msg'
            //             })
            //         }
            //     })
            //         .catch((err) => {
            //             console.log(err);
            //         });
            // }
        },
        handleLogin: async function () {
            this.$v.userInfoLogin.$touch();
            if (this.$v.userInfoLogin.$anyError === false) {
                axios.post(CONFIG.API_URL + `/authen/login`, this.userInfoLogin).then((response) => {
                 
                  console.log("response : ",response)
                        if (response.status == 200 && response.data !== null) {
                            let cookiesExpiredTime = CONFIG.COOKIES_EXPIRED_TIME;
                            window.location.href = CONFIG.CLIENT_URL;
                            window.$cookies.set("username", response.data.data.data.username, cookiesExpiredTime);
                            window.$cookies.set("accessToken", response.data.data.data.accessToken, cookiesExpiredTime);
                            window.$cookies.set("roles", response.data.data.data.roles, CONFIG.COOKIES_EXPIRED_TIME);
                        } else {
                            Swal.fire({
                                icon: 'error',
                                title: 'Oops...',
                                text: response.message[0].msg
                            })
                        }
                    })
                    .catch((err) => {
                        console.log(err);
                    });
            }

        }
    }
}
</script>


<style scoped>
.login-title {
  background-image: linear-gradient(#d4dbf9, #d4d8e6) !important;
}
</style>
