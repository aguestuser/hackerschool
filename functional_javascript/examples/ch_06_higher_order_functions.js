//

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


