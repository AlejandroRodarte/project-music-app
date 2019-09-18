
SELECT * 
	FROM 
		bands
	LEFT JOIN
		albums
	ON
		bands.id = albums.band_id
	LEFT JOIN
		songs
	ON
		albums.id = songs.album_id
	LEFT JOIN
		bands_artists
	ON
		bands.id = bands_artists.band_id
	LEFT JOIN
		artists
	ON
		artists.id = bands_artists.artist_id
	LEFT JOIN
		bands_genres
	ON
		bands.id = bands_genres.band_id
	LEFT JOIN
		genres
	ON
		genres.id = bands_genres.genre_id;