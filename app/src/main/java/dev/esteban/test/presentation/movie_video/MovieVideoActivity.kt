package dev.esteban.test.presentation.movie_video

import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import dev.esteban.test.R
import kotlinx.android.synthetic.main.activity_movie_video.*

class MovieVideoActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private var keyVideo: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_video)

        youtube_view.initialize(getString(R.string.youtube_key), this)

        back_image.setOnClickListener {
            finish()
        }
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        player: YouTubePlayer?,
        wasRestored: Boolean
    ) {
        keyVideo = intent?.extras?.getString(KEY)!!
        if (!wasRestored) {
            player?.cueVideo(keyVideo)
        }
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?, errorReason: YouTubeInitializationResult?) {
        errorReason?.isUserRecoverableError.let{
            errorReason?.getErrorDialog(this, 12)?.show()
        }
    }

    companion object {
        private var KEY: String = "KEY"
        private var NAME: String = "NAME"
    }
}
