package com.example.myapplication

import android.text.Layout
import android.view.LayoutInflater
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.databinding.ActivityMainBinding
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.RoborazziRule
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

private const val OUTPUT_DIRECTORY_PATH = "src/test/snapshots/images"

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [33], qualifiers = RobolectricDeviceQualifiers.Pixel5)
class RoborazziMainTest {
    @get:Rule
    val roborazziRule =
        RoborazziRule(options = RoborazziRule.Options(outputDirectoryPath = OUTPUT_DIRECTORY_PATH))

    private lateinit var binding: ActivityMainBinding
    @Test
    fun testBasic() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)

        scenario.onActivity { activity ->
            binding = ActivityMainBinding.inflate(LayoutInflater.from(activity))
            with (binding) {
                title.text = "This is a title text"
                subtitle.text = "This is a subtitle"
                root.captureRoboImage()
            }
        }
    }
}