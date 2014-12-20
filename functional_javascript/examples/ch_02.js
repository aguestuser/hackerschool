// 99 bottles of beer

var _ = require('underscore')

// var a = {name: "a", fun: function () { return this; }}; 

// var bFunc = function () { return this }
//   , b = {name: "b", fun: bFunc}


// console.log(b.fun())


// APLICATIVE PROGRAMING

// reduceRight for all & any of

function allOf(/* funs */) {
  return _.reduceRight(arguments, function(truth, f) {
    return truth && f(); 
  }, true);
}

function anyOf(/* funs */) {
  return _.reduceRight(arguments, function(truth, f) {
    return truth || f(); 
  }, false);
}

function T() { return true }
function F() { return false }


/// GREAT EXAMPLE!!!! ///

//concatenate args that are arrays
  // NOTE: NOT APPLICATIVE IN AND OF ITSELF (b/c doesn't pass fn as arg)
function cat() {
  var head = _.first(arguments); 
  if (existy(head))
    return head.concat.apply(head, _.rest(arguments)); 
    // does a tricky thing! apply here takes an array of arrays, 
    // but then passes each array as individual arguments
    // that's why head.concat(_.rest) won't work 
    // (b/c it passes an array of arrays instead of a collection of arguments that happen to be arrays)
  else
   return []; 
}

  // USAGE
  // cat([1,2,3], [4,5], [6,7,8]); //=> [1, 2, 3, 4, 5, 6, 7, 8]

// add element to top of array, return new array (sort of like cons)
function construct(head, tail) { 
  return cat([head], _.toArray(tail));
}

// concatenate results of mapping function over array
function mapcat(fn, coll){
  return cat.apply(null, _.map(coll, fn))
}

  // USAGE
  // mapcat(function(e){ 
  //   return construct(e, [','])
  // }, [1,2,3])
  //=> [1, ",", 2, ",", 3, ","]

// interpose splitter between elements of array

function butLast(coll){
  return _.toArray(coll).slice(0, -1)
}

function interpose(splitter, coll){
  return butLast(
    mapcat(function(e){
      return construct(e, [splitter])
    }, coll)
  )
}

  //USAGE
  interpose(",", [1,2,3]);
  //=> [1, ",", 2, ",", 3]



