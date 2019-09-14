USE music_db;

CREATE OR REPLACE VIEW band_song_count_view AS 
	SELECT 
		bands.id AS id,
		COUNT(*) AS song_count
    FROM 
		bands
	INNER JOIN 
		albums 
	ON 
		bands.id = albums.band_id
	INNER JOIN
		songs
	ON
		albums.id = songs.album_id
	GROUP BY 
		albums.band_id;
    