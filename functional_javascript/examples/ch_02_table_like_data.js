// TABLE LIKE DATA

var _ = require('underscore');

// dependencies:
function cat() {
  var head = _.first(arguments); 
  if (existy(head)){ return head.concat.apply(head, _.rest(arguments)) }
  else { return [] }
}

function construct(head, tail) { 
  return cat([head], _.toArray(tail));
}

var library = [
  {title: "SICP", isbn: "0262010771", ed: 1}, 
  {title: "SICP", isbn: "0262510871", ed: 2},
  {title: "Joy of Clojure", isbn: "1935182641", ed: 1}
];

// cool stuff:

// _.pluck on the above which would yield:
  _.pluck(library, 'title');
  //=> ["SICP", "SICP", "Joy of Clojure"]
  // (which destructures the abstraction of the table as an invariant)

//***************************************************//
//***************************************************//
function project(table, keys) { 
  return _.map(table, function(obj) {
    // table : [objs] -> obj : Obj -> [objs]
    return _.pick.apply(null, construct(obj, keys)); 
    // () -> ArgList[ Obj, key, key, key, ... ] -> Obj (with less keys)
  });
};
//***************************************************//
//***************************************************//

  //USAGE
  var editionResults = project(library, ['title', 'isbn']);
  editionResults;
  //=> [{isbn: "0262010771", title: "SICP"},
  // {isbn: "0262510871", title: "SICP"},
  // {isbn: "1935182641", title: "Joy of Clojure"}];

  var isbnResults = project(editionResults, ['isbn']); isbnResults;
  //=> [{isbn: "0262010771"},{isbn: "0262510871"},{isbn: "1935182641"}]

  // if, after this, we want to intentionally break the abstraction 
  // to get usable output, we can do:
  var isbnResults = project(editionResults, ['isbn']); 
  isbnResults;
  //=> [{isbn: "0262010771"},{isbn: "0262510871"},{isbn: "1935182641"}]


//***************************************************//

function as(table, newNames){
  return _.map(table, function(obj){
    return rename(obj, newNames);
  });
}

function rename(obj, newNames) {
  return _.reduce(newNames, function(o, nu, old) {
    if (_.has(obj, old)) {
      o[nu] = obj[old];
      return o;
    }
    else
      return o;
  },
  _.omit.apply(null, construct(obj, _.keys(newNames))));
};

//***************************************************//

  // USAGE
  project(as(library, {ed: 'edition'}), ['edition']);
  //=> [{edition: 1}, {edition: 2}, {edition: 1}];


//***************************************************//
//***************************************************//
function restrict(table, pred) {
  return _.reduce(table, function(newTable, obj) {
    if (truthy(pred(obj))) return newTable;
    else
      return _.without(newTable, obj);
  }, table);
};

//***************************************************//
//***************************************************//

// USAGE:

restrict(library, function(book) {
  return book.ed > 1;
});
//=> [{title: "SICP", isbn: "0262510871", ed: 2}]


/// PUTTING IT ALL TOGETHER:

restrict(
  project(
    as(library, {ed: 'edition'}),
    ['title', 'isbn', 'edition']),
  function(book) {
    return book.edition > 1;
  });

//=> [{title: "SICP", isbn: "0262510871", edition: 2},]
