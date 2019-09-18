USE music_db;

CREATE OR REPLACE VIEW bands_count_view AS
	SELECT 
		bands.id AS id,
        bands.name AS name,
        bands.image_path AS image_path,
        bands.origin_country AS origin_country,
        bands.origin_year AS origin_year,
		COUNT(DISTINCT albums.id) AS album_count,
		COUNT(DISTINCT songs.id) AS song_count,
        bands.created_at AS created_at,
        bands.updated_at AS updated_at
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