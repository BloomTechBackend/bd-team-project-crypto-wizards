module.exports = {
    getUsername: function() {
        return sessionStorage.getItem('username');
    },

    getToken: function () {
        return sessionStorage.getItem('token');
    },

    isNewUser: function() {
        return sessionStorage.getItem('newUser');
    },

    setUserSession: function (username, token, newUser) {
        sessionStorage.setItem('username', username);
        sessionStorage.setItem('token', token);
        sessionStorage.setItem('newUser', newUser);
    },

    resetUserSession: function () {
        sessionStorage.removeItem('username');
        sessionStorage.removeItem('token');
        sessionStorage.removeItem('newUser');
    }
}