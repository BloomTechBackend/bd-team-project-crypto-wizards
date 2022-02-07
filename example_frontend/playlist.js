const addAlbumTrackForm = document.querySelector("#add-album-track-form");
const albumTrackTable = document.querySelector("#album-track-table");
const urlParams = new URLSearchParams(window.location.search);
const id = urlParams.get('id');

addAlbumTrackForm.onsubmit = function(evt) {
  evt.preventDefault();
  const asin = document.querySelector("#album-asin").value;
  const trackNumber = document.querySelector("#track-number").value;
  const newSong = {
    "asin": asin,
    "trackNumber": trackNumber,
    "queueNext": false
  }
  axios.post(`https://svebsuap66.execute-api.us-west-2.amazonaws.com/prod/playlists/${id}/songs`, newSong, {
    authorization: {
      'x-api-key': 'K7CHRL6aqt1C6eGJ9EHyFaZCn86G0fyI2sTZKSkW'
    }
  })
  .then(res => {
    console.log(res);
    window.location.reload();
  });
}

window.onload = async function(evt) {
  evt.preventDefault();
  console.log("Getting Album Track Data...");
  axios.get("https://svebsuap66.execute-api.us-west-2.amazonaws.com/prod/playlists/"+id+"/songs", {
    authorization: {
      'x-api-key': 'K7CHRL6aqt1C6eGJ9EHyFaZCn86G0fyI2sTZKSkW'
    }
  }).then(res => {
    console.log(res);
    if (!res.data) {
      throw "No data for playlist with id:" + id;
    }

    if (res.data.songList.length > 0) {
      populateAlbumTracks(res.data.songList);
    }
  })
}

function populateAlbumTracks(albumTracksData) {
  let thead = albumTrackTable.createTHead();
  let tbody = albumTrackTable.createTBody();
  let row = thead.insertRow();

  for (let key in albumTracksData[0]) {
    let th = document.createElement("th");
    let text = document.createTextNode(key);
    th.appendChild(text);
    row.appendChild(th);
  }

  for (let albumTrack of albumTracksData) {
    let row = tbody.insertRow();
    for (key in albumTrack) {
      let cell = row.insertCell();
      let text = document.createTextNode(albumTrack[key]);
      cell.appendChild(text);
    }
  }
}
