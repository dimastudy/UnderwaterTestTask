package com.justadev.underwaterworldtesttask.presentation.screen.game

import androidx.compose.animation.core.Animatable
import androidx.compose.ui.graphics.ImageBitmap
import com.justadev.underwaterworldtesttask.core.BaseViewModel
import com.justadev.underwaterworldtesttask.core.utils.isCollisionV2
import com.justadev.underwaterworldtesttask.domain.model.FallingObject
import com.justadev.underwaterworldtesttask.domain.model.FallingObstacle
import com.justadev.underwaterworldtesttask.domain.model.FallingScoreBonus
import com.justadev.underwaterworldtesttask.domain.model.FallingTimeBonus
import com.justadev.underwaterworldtesttask.domain.model.GameStatus
import com.justadev.underwaterworldtesttask.domain.model.SHIP_HEIGHT
import com.justadev.underwaterworldtesttask.domain.model.SHIP_WIDTH
import com.justadev.underwaterworldtesttask.domain.model.Ship
import kotlinx.coroutines.delay
import kotlin.random.Random


class GameViewModel : BaseViewModel<GameState, GameEvent>(GameState()) {


    override fun onEvent(intent: GameEvent) {
        when (intent) {
            is GameEvent.SpawnFallingObject -> spawnFallingObjects(
                intent.screenWidth, intent.stoneImage, intent.bonusImage, intent.sandClockImage
            )

            is GameEvent.MoveFallingObjects -> moveFallingObjects(intent.moveAction)
            is GameEvent.SetupShipPosition -> setUpShipPosition(
                intent.screenWidth, intent.screenHeight
            )

            is GameEvent.MoveShip -> moveShip(intent.moveAction)
            GameEvent.CheckCollision -> checkCollision()
            GameEvent.StartGame -> {
                updateState { copy(game = game.copy(status = GameStatus.Started)) }
            }

            GameEvent.DecreaseTime -> {
                updateState {
                    copy(timeLeft = timeLeft - 1)
                }
                addScoreTime()
            }

            GameEvent.EndGame -> endGame(true)
        }
    }

    private fun endGame(isWin: Boolean) {
        updateState {
            copy(game = game.copy(status = GameStatus.Over), isWinGame = isWin)
        }
    }


    private fun spawnFallingObjects(
        screenWidth: Int,
        stoneImage: ImageBitmap,
        bonusImage: ImageBitmap,
        sandClockImage: ImageBitmap
    ) {
        val randomSpawn = Random.nextInt(0, 101)
        when (randomSpawn) {
            in 0..29 -> {
                val randomX = (0..screenWidth - FallingScoreBonus.WIDTH).random()
                val listObjects = currentState.fallingObjects.toMutableList()
                listObjects.add(
                    FallingScoreBonus(
                        x = randomX.toFloat(),
                        y = Animatable(0f),
                        radius = 100f,
                        fallingSpeed = currentState.game.settings.fallingSpeed,
                        image = bonusImage
                    )
                )
                updateState {
                    copy(
                        fallingObjects = listObjects
                    )
                }
            }

            in 30..49 -> {
                val randomX = (0..screenWidth - FallingTimeBonus.WIDTH).random()
                val listObjects = currentState.fallingObjects.toMutableList()
                listObjects.add(
                    FallingTimeBonus(
                        x = randomX.toFloat(),
                        y = Animatable(0f),
                        radius = 100f,
                        fallingSpeed = currentState.game.settings.fallingSpeed,
                        image = sandClockImage
                    )
                )
                updateState {
                    copy(
                        fallingObjects = listObjects
                    )
                }
            }

            else -> {
                val randomX = (0..screenWidth - FallingObstacle.WIDTH).random()
                val listObjects = currentState.fallingObjects.toMutableList()
                listObjects.add(
                    FallingObstacle(
                        x = randomX.toFloat(),
                        y = Animatable(0f),
                        radius = 75f,
                        fallingSpeed = currentState.game.settings.fallingSpeed,
                        image = stoneImage
                    )
                )
                updateState {
                    copy(
                        fallingObjects = listObjects
                    )
                }
            }
        }
    }

    private fun moveFallingObjects(moveAction: (List<FallingObject>) -> Unit) {
        val list = currentState.fallingObjects.toMutableList()
        moveAction(list)
        updateState { copy(fallingObjects = list) }
    }

    private fun setUpShipPosition(width: Int, height: Int) {
        updateState {
            copy(
                ship = ship.copy(
                    x = Animatable(
                        initialValue = ((width.toFloat() / 2) - (SHIP_WIDTH / 2))
                    ), y = height - SHIP_HEIGHT - (SHIP_HEIGHT / 2f)
                )
            )
        }
    }


    private fun moveShip(moveAction: (Ship) -> Unit) {
        val ship = currentState.ship.copy()
        moveAction(ship)
        updateState { copy(ship = ship) }
    }

    private fun checkCollision() {
        val fallingIterator = currentState.fallingObjects.iterator()
        while (fallingIterator.hasNext()) {
            val fallingObject = fallingIterator.next()
            val isCollision = isCollisionV2(currentState.ship, fallingObject)
            if (isCollision) {
                if (fallingObject is FallingObstacle) {
                    updateState {
                        copy(lives = lives - 1)
                    }
                    if (currentState.lives <= 0) {
                        endGame(false)
                    }
                }
                if (fallingObject is FallingTimeBonus) {
                    launchSafely(block = {
                        updateState { copy(timeLeft = timeLeft + 5, timeBonus = true) }
                        delay(750)
                        updateState { copy(timeBonus = false) }
                    })
                }
                if (fallingObject is FallingScoreBonus) {
                    addBonusScore()
                }
                removeFallingObject(fallingObject)
            }
        }
    }

    private fun removeFallingObject(fallingObject: FallingObject) {
        val list = currentState.fallingObjects.toMutableList()
        list.remove(fallingObject)
        updateState {
            copy(fallingObjects = list)
        }
    }

    private fun addScoreTime() {
        addScore(1)
    }

    private fun addBonusScore() {
        launchSafely(block = {
            addScore(5)
            updateState { copy(scoreBonus = true) }
            delay(750)
            updateState { copy(scoreBonus = false) }
        })

    }

    private fun addScore(value: Int) {
        updateState {
            copy(game = game.copy(score = game.score + value))
        }
    }

}