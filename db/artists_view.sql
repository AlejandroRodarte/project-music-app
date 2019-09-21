USE music_db;

CREATE OR REPLACE VIEW artists_view AS
	SELECT 
		artists_band_genre_view.id AS id,
		artists_band_genre_view.first_name AS first_name,
		artists_band_genre_view.last_name AS last_name,
		artists_band_genre_view.image_path AS image_path,
		artists_band_genre_view.created_at AS created_at,
		artists_band_genre_view.updated_at AS updated_at,
		artists_count_view.band_count AS band_count,
		artists_band_genre_view.band_name AS band_name,
		artists_band_genre_view.genre_name AS genre_name
			FROM
				artists_band_genre_view
			LEFT JOIN
				artists_count_view
			ON
				artists_band_genre_view.id = artists_count_view.id;