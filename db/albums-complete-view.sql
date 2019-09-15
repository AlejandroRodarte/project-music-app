USE music_db;

CREATE OR REPLACE VIEW albums_view AS 
	SELECT 
		albums.id AS id,
		albums.name AS name,
		albums.image_path AS image_path,
		albums.release_year AS release_year,
		albums.band_id AS band_id,
        albums.created_at AS created_at,
        albums.updated_at AS updated_at,
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