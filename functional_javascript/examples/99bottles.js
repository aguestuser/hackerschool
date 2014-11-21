var _ = require('underscore')

;(function main(){
  var start = +process.argv[2]
    , end = +process.argv[3]
    , version = process.argv[4]
    , a_song = song(start, end, version)
  console.log(a_song)
})()


// my version

function song(start, end, version) { 
  return _.chain(_.range(start, end, -1))
    .map(songVersions(version))
    .flatten()
    .value()
}

function songVersions(version){
  return {
    standard: function(n){
      return [
        n + ' bottles of beer on the wall',
        n + ' bottles of beer',
        'Take one down, pass it around',
        n > 1 ? (n -1) + ' bottles of beer on the wall' : 'No more bottles of beer on the wall'
      ]
    },
    crazy: function(n){
      return [
        n + ' kegs of whisky on the wall',
        n + ' vats of obscenely good absynthe',
        'Take one down, pass it around',
        n > 1 ? (n -1) + ' cops in your head' : 'No more cops in your head'
      ]
    }
  }[version]
}

// BOOK VERSION:

// function lyricSegment(n){
//   return [
//     n + ' bottles of beer on the wall',
//     n + ' bottles of beer',
//     'Take one down, pass it around',
//     n > 1 ? (n -1) + ' bottles of beer on the wall' : 'No more bottles of beer on the wall'
//   ]
// }

// book version

// function song(start, end, lyricGen) { 
//   return _.reduce(_.range(start, end, -1),
//     function(acc,n) {
//       return acc.concat(lyricGen(n));
//     }, []); 
// }

// function lyricSegment(n){
//   return _.chain([])
//     .push(n + ' bottles of beer on the wall')
//     .push(n + ' bottles of beer')
//     .push('Take one down, pass it around')
//     .tap(function(lyrics){
//       if (n > 1)
//         lyrics.push((n - 1) + ' bottles of beer on the wall')
//       else
//         lyrics.push('No more bottles of beer on the wall')
//     })
//     .value()
// }