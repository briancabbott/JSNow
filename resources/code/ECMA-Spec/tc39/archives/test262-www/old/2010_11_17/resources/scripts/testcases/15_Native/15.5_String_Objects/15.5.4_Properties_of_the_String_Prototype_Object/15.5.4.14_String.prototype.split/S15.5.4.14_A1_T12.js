// Copyright 2009 the Sputnik authors.  All rights reserved.
// This code is governed by the BSD license found in the LICENSE file.

/**
* @name: S15.5.4.14_A1_T12;
* @section: 15.5.4.14;
* @assertion: String.prototype.split(separator, limit):
* i) can be transferred to other kinds of objects for use as a method.
* separator and limit can be any kinds of object since:
* ii) if separator is not RegExp ToString(separator) performs and
* iii) ToInteger(limit) performs;
* @description: Arguments are objects, and instance is string. 
* First object have overrided toString function. 
* Second object have overrided valueOf function and toString function, that throw exception;
*/


// Converted for Test262 from original Sputnik source

ES5Harness.registerTest( {
id: "S15.5.4.14_A1_T12",

path: "15.5.4.14",

description: "Arguments are objects, and instance is string.",

test: function testcase() {
   var __obj = {toString:function(){return "\u0041B";}}
var __obj2 = {valueOf:function(){return {};},toString:function(){throw "intointeger";}}
var __str = new String("ABB\u0041BABAB");

//////////////////////////////////////////////////////////////////////////////
//CHECK#1
with(__str){
    try {
      var x = split(__obj, __obj2);
      $FAIL('#1: "var x = split(__obj, __obj2)" lead to throwing exception');
    } catch (e) {
      if (e!=="intointeger") {
        $ERROR('#1.1: Exception === "intointeger". Actual: '+e);
      }
    }
}
//
//////////////////////////////////////////////////////////////////////////////

 }
});

