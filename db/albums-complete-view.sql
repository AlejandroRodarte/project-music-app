USE music_db;

CREATE OR REPLACE VIEW albums_view AS 
	SELECT 
		albums.id AS album_id,
		albums.name AS album_name,
		albums.image_path AS album_image_path,
		albums.release_year AS album_release_year,
		albums.band_id AS band_id,
		bands.name AS band_name,
		bands.image_path AS band_image_path,
		COUNT(songs.id) AS song_count
	FROM
		albums 
	LEFT JOIN 
		bands 
	ON 
		albums.band_id = bands.id 
	LEFT JOIN 
		songs 
	ON 
		songs.album_id = albums.id
	GROUP BY 
		albums.id;