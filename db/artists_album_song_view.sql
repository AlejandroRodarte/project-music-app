CREATE OR REPLACE VIEW bands_album_song_view AS
	SELECT 
		bands.id AS id,
        albums.name AS album_name,
        songs.name AS song_name
			FROM
				bands
			LEFT JOIN
				albums
			ON
				bands.id = albums.band_id
			LEFT JOIN
				songs
			ON
				albums.id = songs.album_id;
				