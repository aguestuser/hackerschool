// CLOSURE EXAMPLES

// synthesize complement

function complement(PRED) {
  return function() {
    return !PRED.apply(null, _.toArray(arguments));
  };
}

//usage:
function isEven(n) { return (n%2) === 0; }
var isOdd = complement(isEven);
isOdd(2);//=> false

// could also make hasOddMembers(array) & synthesize its complement quickly...

////encapsulation, privacy

var pingpong = (function() {
  var PRIVATE = 0;
  return {
    inc: function(n) {
      return PRIVATE += n;
    },
    dec: function(n) {
      return PRIVATE -= n;
    }
  };
})();

//usage:
pingpong.inc(10); //=> 10
pingpong.dec(7); //=> 3
pingpong.div = function(n) { return PRIVATE / n };
pingpong.div(3); // => ReferenceError: PRIVATE is not defined

/// 


/// abstraction (higher order fns)

function plucker(FIELD) {
  return function(obj) {
    return (obj && obj[FIELD]);
  };
}

// usage:

var books = [{title: "Chthon"}, {stars: 5}, {title: "Botchan"}];
var getTitle = plucker('title');

//for plucking title fields:

books.map(getTitle); // => ["Chton", "Botchan"]

// for filtering on objs that have a title field

books.filter(getTitle); // => [{title: "Chton"}, {title: "Botchan"}]

// two purposes made possible by the way __ && __ is evaluated (returns r/h side if left true)
