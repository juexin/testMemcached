'use strict';

angular.module('webappApp')
  .controller('MainCtrl', function ($scope, $http) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
    $scope.save=save;

    $http.get('http://localhost:8080/persons').
    success(function(data) {
        $scope.persons = data;
    });

    function save(){
      var person={
        firstName:$scope.firstName,
        lastName:$scope.lastName,
        profession:$scope.profession,
        companies:[{
          orgName:$scope.orgName,
          headquarter:$scope.headquarter
        }]
      }
      $http({
        method:'POST',
        url:'http://localhost:8080/saveperson',
        data:person,
        headers: {

          'Content-Type': 'application/x-www-form-urlencoded',

        }
      }). success(function(data) {
        $scope.persons = data;
      });


    }
  });
