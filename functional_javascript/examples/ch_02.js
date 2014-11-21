// 99 bottles of beer

var _ = require('underscore')

var a = {name: "a", fun: function () { return this; }}; 

var bFunc = function () { return this }
  , b = {name: "b", fun: bFunc}


console.log(b.fun())
// console.log(prettyPrint(a.fun()))
// console.log(JSON.stringify({a:'alabaster', b:'beer'}))

// function prettyPrint(obj){
//   return JSON.stringify(obj)
// }