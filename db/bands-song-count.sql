USE music_db;

CREATE OR REPLACE VIEW band_song_count_view AS 
	SELECT 
		bands.id AS id,
		COUNT(songs.album_id) AS song_count
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
	WHERE
		songs.album_id IS NOT NULL
	GROUP BY 
		bands.id;
    