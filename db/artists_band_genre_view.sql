USE music_db;

CREATE OR REPLACE VIEW artists_band_genre_view AS
	SELECT 
		artists.id AS id,
		artists.first_name AS first_name,
		artists.last_name AS last_name,
		artists.image_path AS image_path,
		artists.created_at AS created_at,
		artists.updated_at AS updated_at,
		bands.name AS band_name,
		genres.name AS genre_name
			FROM
				artists
			LEFT JOIN
				bands_artists
			ON
				artists.id = bands_artists.artist_id
			LEFT JOIN
				bands
			ON
				bands.id = bands_artists.band_id
			LEFT JOIN
				bands_genres
			ON
				bands.id = bands_genres.band_id
			LEFT JOIN
				genres
			ON
				genres.id = bands_genres.genre_id;