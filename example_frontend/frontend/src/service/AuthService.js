module.exports = {
    getUsername: function() {
        return sessionStorage.getItem('username');
    },

    getToken: function () {
        return sessionStorage.getItem('token');
    },

    setUserSession: function (username, token) {
        sessionStorage.setItem('username', username);
        sessionStorage.setItem('token', token);
    },

    resetUserSession: function () {
        sessionStorage.removeItem('username');
        sessionStorage.removeItem('token');
    }
}