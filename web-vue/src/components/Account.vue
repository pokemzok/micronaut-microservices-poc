<template>
    <!--<div class="col-sm-4 col-sm-offset-4">-->
    <div>
        <div class="form-container" v-if="!auth.user.authenticated">
            <h2>Log in to your account</h2>
            <b-form @submit="login">
                <b-form-group id="usernameGroup"
                              label="Username:"
                              label-for="userName">
                    <b-form-input id="userName"
                                  type="text"
                                  v-model="credentials.username"
                                  required
                                  placeholder="Enter username">
                    </b-form-input>
                </b-form-group>

                <b-form-group id="passwordGroup"
                              label="Password:"
                              label-for="password">
                    <b-form-input id="password"
                                  type="password"
                                  v-model="credentials.password"
                                  required
                                  placeholder="Enter password">
                    </b-form-input>
                </b-form-group>

                <b-button type="submit" variant="primary">Login</b-button>
            </b-form>

        </div>
        <div class="form-container" v-else>
            <b-form @submit="logout">
                <b-button type="submit" variant="primary">Logout</b-button>
            </b-form>
        </div>
    </div>

</template>

<script>
    import auth from './http/Auth'
    export default {
        data() {
            return {
                credentials: {
                    username: '',
                    password: ''
                },
                error: '',
                auth: auth,
            }
        },
        methods: {
            login() {
                var credentials = {
                    username: this.credentials.username,
                    password: this.credentials.password
                }
                auth.login(this, credentials)
            },
            logout() {
                auth.logout()
            }
        }

    }
</script>