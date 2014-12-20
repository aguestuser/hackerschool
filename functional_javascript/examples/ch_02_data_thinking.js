//data thinking

// upshot: use simple data structures 
// with uniform associative modeling rules
// for great good!

// alternative to less flexible class/prototype ways of thinking



var zombie = {name: "Bub", film: "Day of the Dead"}

// PAIRS

_.pairs(zombie);
//=> [["name", "Bub"], ["film", "Day of the Dead"]]

_.object(_.map(_.pairs(zombie), function(pair) {
  return [pair[0].toUpperCase(), pair[1]];
  })
);
//=> {FILM: "Day of the Dead", NAME: "Bub"};


// DEFAULTS
_.pluck( _.map([
    {title: "Chthon", author: "Anthony"},
    {title: "Grendel", author: "Gardner"},
    {title: "After Dark"}
  ], function(obj) {
    return _.defaults(obj, {author: "Unknown"}) 
  })
,'author');
//=> ["Anthony", "Gardner", "Unknown"]

// PICK/OMIT

var person = {name: "Romy", token: "j3983ij", password: "tigress"};
var info = _.omit(person, 'token', 'password'); 
info;
//=> {name: "Romy"}

var creds = _.pick(person, 'token', 'password'); 
creds;
//=> {password: "tigress", token: "j3983ij"};
