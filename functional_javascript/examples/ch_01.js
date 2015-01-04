// synthetic generic comparator

function lessOrEqual(x, y) {
  return x <= y;
}

function comparator(pred) {
  return function(x, y) {
    if (pred(x, y))
      return -1;
    else if (pred(y, x))
      return 1;
    else
      return 0;
  };
};

  //usage:
  [100, 1, 0, 10, -1, -2, -1].sort(comparator(lessOrEqual)) // => [-2, -1, -1, 0, 1, 10, 100]

// existy / truthy

function existy(x) { return x != null };

  //usage:
  existy(null);//=> false
  existy(undefined); //=> false
  existy({}.notHere); //=> false
  existy((function(){})()); //=> false
  existy(0); //=> true
  existy(false); //=> true

function truthy(x) { return (x !== false) && existy(x) };

  //usage:
  truthy(false); //=> false
  truthy(undefined); //=> false
  truthy(0); //=> true
  truthy(''); //=> true

// application of truthy to a "do something if it's a thing, otherwise return undefined" pattern:

function doWhen(cond, action) {
  if(truthy(cond)){
    return action();
  }
  else{
    return undefined;
  }
 }

function executeIfHasField(target, name) {
  return doWhen(existy(target[name]), function() {
    var result = _.result(target, name); // will invoke target[name] if name is a function, otherwise return value of target[name]
    console.log(['The result is', result].join(' '));
    return result;
  });
}

  // usage:
  executeIfHasField([1,2,3], 'reverse'); // (console) The result is 3, 2, 1 //=> [3, 2, 1]
  executeIfHasField({foo: 42}, 'foo'); // (console) The result is 42 //=> 42
  executeIfHasField([1,2,3], 'notHere'); //=> undefined

function fail(thing) { throw new Error(thing); }
function warn(thing) { console.log(["WARNING:", thing].join(' ')); }
function note(thing) { console.log(["NOTE:", thing].join(' ')); }
