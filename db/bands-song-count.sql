USE music_db;

CREATE OR REPLACE VIEW band_song_count_view AS 
	SELECT 
		bands.id AS id,
		CASE
			WHEN songs.album_id IS NULL THEN 0
            ELSE COUNT(*)
		END AS song_count
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
	GROUP BY 
		bands.id;
    