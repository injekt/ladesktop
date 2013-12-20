var exec = require('nuwa/exec');

module.exports = {
  getNativeAppList : function(successCallback, errorCallback) {
    exec(successCallback, errorCallback, 'launcher', 'getNativeAppList', []);
  },
  startApp : function(successCallback, errorCallback, packgeName) {
    exec(successCallback, errorCallback, 'launcher', 'startApp', [packageName]);
  },
};
