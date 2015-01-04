// dependencies
function existy(x) { return x != null; };
function truthy(x) { return (x !== false) && existy(x); };
function doWhen(cond, action) {
  if(truthy(cond)){
    return action();
  }
  else{
    return undefined;
  }
}
function fail(thing) { throw new Error(thing); }
var _ = require('underscore');

// uses plucker from last ch 3

function finder(valueFun, bestFun, coll) {
  return _.reduce(coll, function(best, current) {
    var bestValue = valueFun(best);
    var currentValue = valueFun(current);
    return (bestValue === bestFun(bestValue, currentValue)) ? best : current;
  });
}

    //usage:
    finder(plucker('name'),
           function(x,y) { return (x.charAt(0) === "L") ? x : y },
           people);
    //=> {name: "Lucy", age: 36}

//////

function best(fun, coll){
  return _.reduce(coll, function(x,y){
    return fun(x,y) ? x : y;
  });
}

//usage:
best(function(x,y) { return x > y ;}, [1,2,3,4,5]); //=> 5


///REPEATEDLY

function repeatedly(times, fn) {
  return _.map(_.range(times), fn);
}

    //USAGE:

    repeatedly(3, function() {
      return Math.floor((Math.random()*10)+1);
    });
    //=> [1, 3, 8]

    repeatedly(3, function(n) {
      var id = 'id' + n; $('body').append($("<p>Odelay!</p>").attr('id', id));
      return id;
    });
    // Page now has three Odelays
    //=> ["id0", "id1", "id2"]

//ITERATE UNTIL

function iterateUntil(fun, check, init) {
  var ret = [];
  var result = fun(init);
  while (check(result)) {
    ret.push(result);
    result = fun(result);
  }
  return ret;
};

// USAGE:

iterateUntil(
  function(n) { return n+n },
  function(n) { return n <= 1024 },
  1);
//=> [2, 4, 8, 16, 32, 64, 128, 256, 512, 1024]
// what's cool: you didn't need to know how many iterations
// it would take to get to 1024!

// ALWAYS

function always(value){
  return function(){
    return value;
  };
}
  // USAGE:
  // function(){} returns unique value:

  var f = always(function(){}),
      g = always(function(){});

  f() === f(); // => true
  g() === f(); // => false

// INVOKER

function invoker(name, method){
  return function(target /*args*/){
    if (!existy(target)) fail("Must provide a target!");

    var targetMethod = target[name],
        args = _.rest(arguments);

    return doWhen(
      (existy(targetMethod) && method === targetMethod), function(){
        return targetMethod.apply(target, args);
      }
    );
  };
};

  //USAGE:

  var rev = invoker('reverse', Array.prototype.reverse);
  _.map([[1,2,3]], rev); // => [[3,2,1]]

// FNULL

function fnull(fn /*, defaults*/){
  var defaults = _.rest(arguments);

  return function(/* arguments */){
    var args = _.map(arguments, function(e, i){
      return existy(e) ? e : defaults[i];
    });

    return fn.apply(null, args);
  };
}

    // USAGE: setting optional args

    function defaults(d){
      return function(o,k){
        var val = fnull(_.identity, d[k]);
        return o && val(o[k]);
      };
    }

    function doSomething(config){
      var lookup = defaults({critical: 108});
      return lookup(config, 'critical');
    }

    doSomething({critical: 9}); // => 9
    doSomething({}); // => 108
