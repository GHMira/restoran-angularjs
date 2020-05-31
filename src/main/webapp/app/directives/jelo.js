angular.module('crudApp').directive('jelo', [function jelo() {

    return {
        restrict: 'E',
        scope: {
        },

        templateUrl: 'app/templates/jelo.html',
        controller: 'GeneralController'

    };
}]);