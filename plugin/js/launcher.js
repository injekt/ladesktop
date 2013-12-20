var exec = require('nuwa/exec');

module.exports = {
  getNativeAppList : function(successCallback, errorCallback) {
    exec(successCallback, errorCallback, 'launcher', 'getNativeAppList', []);
  },
};
