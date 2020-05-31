angular.module('crudApp').directive('konobar', [function konobar() {

    return {
        restrict: 'E',
        scope: {
        },
        templateUrl: 'app/templates/konobar.html',
        controller: 'GeneralController'

    };
}]);