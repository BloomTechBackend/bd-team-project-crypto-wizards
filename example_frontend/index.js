const playlistForm = document.querySelector("#create-playlist-form");
const playlistsList = document.querySelector("#playlists");

playlistForm.onsubmit = async function(evt) {
  evt.preventDefault();
  const name = document.querySelector("#playlist-name").value;
  const customerId = "testCustomer";
  const playlistObj = {
    "name": name,
    "customerId": customerId,
    "songCount": 0
  }
  axios.post("https://svebsuap66.execute-api.us-west-2.amazonaws.com/prod/playlists", playlistObj, {
    authorization: {
      'x-api-key': 'K7CHRL6aqt1C6eGJ9EHyFaZCn86G0fyI2sTZKSkW'
    }
  }).then((res) => {
    console.log(res);
    window.location.reload();
  })
}

window.onload = async function(evt) {
  evt.preventDefault();
  console.log("Getting Playlist Data...");
  axios.get("https://svebsuap66.execute-api.us-west-2.amazonaws.com/prod/playlists", {
    authorization: {
      'x-api-key': 'K7CHRL6aqt1C6eGJ9EHyFaZCn86G0fyI2sTZKSkW'
    }
  }).then((res) => {
    console.log(res.data);
    populatePlaylists(res.data.playlists);
  })
}

function populatePlaylists(playlistData) {

  for (let playlist of playlistData) {
    let li = document.createElement("li");
    let a = document.createElement("a");
    let text = document.createTextNode(playlist.name);

    a.setAttribute('href', `./playlist.html?id=${playlist.id}`);

    a.appendChild(text);
    li.appendChild(a);
    playlistsList.appendChild(li);
  }
}
