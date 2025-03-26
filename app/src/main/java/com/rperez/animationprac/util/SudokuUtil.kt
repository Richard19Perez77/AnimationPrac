package com.rperez.animationprac.util

import kotlin.random.Random

class SudokuUtil {

    var gridValues = IntArray(81) { 0 }
    val fullList = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9)
    var editingList: MutableList<Int> = fullList.toMutableList()

    fun createGrid(): IntArray {
        var validRows = 0
        createGridLine1()
        validRows++
        createGridLine2()
        validRows++
        createGridLine3()
        validRows++
        createGridLine4()
        return gridValues
    }

    fun createGridLine4() {
        // remove valid values from grid col and row
        // if row becomes empty can call previous row
    }

    fun createGridLine3() {
        // can use all but remove more values from the editing list
        // item's for row three can't have the same grid row and column
        // will only need to work about grid and row for 1st 3 grid in row
        // first valid impossible grid creation
        editingList = fullList.toMutableList()

        var removedList = mutableListOf<Int>()
        removedList.add(gridValues[0])
        removedList.add(gridValues[1])
        removedList.add(gridValues[2])
        removedList.add(gridValues[9])
        removedList.add(gridValues[10])
        removedList.add(gridValues[11])

        editingList.removeAll(removedList)
        var i = 18
        while (i < 20) {
            var randomIndex = Random.nextInt(1, editingList.size)
            var pulledValue = editingList[randomIndex]
            editingList.remove(randomIndex)
            gridValues[i] = pulledValue
            i++
        }

        editingList.addAll(removedList)
        removedList.clear()
        removedList.add(gridValues[3])
        removedList.add(gridValues[4])
        removedList.add(gridValues[5])
        removedList.add(gridValues[12])
        removedList.add(gridValues[13])
        removedList.add(gridValues[14])
        editingList.removeAll(removedList)
        while (i < 24) {
            var randomIndex = Random.nextInt(1, editingList.size)
            var pulledValue = editingList[randomIndex]
            editingList.remove(randomIndex)
            gridValues[i] = pulledValue
            i++
        }

        editingList.addAll(removedList)
        removedList.clear()
        removedList.add(gridValues[6])
        removedList.add(gridValues[7])
        removedList.add(gridValues[8])
        removedList.add(gridValues[15])
        removedList.add(gridValues[16])
        removedList.add(gridValues[17])
        editingList.removeAll(removedList)
        while (i < 27) {
            var randomIndex = Random.nextInt(1, editingList.size)
            var pulledValue = editingList[randomIndex]
            editingList.remove(randomIndex)
            gridValues[i] = pulledValue
            i++
        }
    }

    fun createGridLine2() {
        // can pull numbers but there two invalid numbers for each index
        // it can't be in same 1 to 9 grid or row
        editingList = fullList.toMutableList()
        // if index 10 is col 1 row 2
        // valid number can't be grid or row
        // using full list prevents row duplicate
        // grid at this point for index 0
        var removedList = mutableListOf<Int>()
        removedList.add(gridValues[0])
        removedList.add(gridValues[1])
        removedList.add(gridValues[2])
        editingList.removeAll(removedList)
        // can't use values from the first 3 in grid
        // fill in next 3
        var i = 8
        while (i < 12) {
            var randomIndex = Random.nextInt(1, editingList.size)
            var pulledValue = editingList[randomIndex]
            editingList.remove(randomIndex)
            gridValues[i] = pulledValue
            i++
        }
        // fill in next 3 and an now use removed list
        editingList.addAll(removedList)
        // remove next 3 from row 1
        removedList.clear()
        removedList.add(gridValues[3])
        removedList.add(gridValues[4])
        removedList.add(gridValues[5])
        editingList.removeAll(removedList)
        while (i < 15) {
            var randomIndex = Random.nextInt(1, editingList.size)
            var pulledValue = editingList[randomIndex]
            editingList.remove(randomIndex)
            gridValues[i] = pulledValue
            i++
        }
        // same for last 3 in row 2
        // fill in next 3 and an now use removed list
        editingList.addAll(removedList)
        // remove next 3 from row 1
        removedList.clear()
        removedList.add(gridValues[6])
        removedList.add(gridValues[7])
        removedList.add(gridValues[8])
        editingList.removeAll(removedList)
        while (i < 18) {
            var randomIndex = Random.nextInt(1, editingList.size)
            var pulledValue = editingList[randomIndex]
            editingList.remove(randomIndex)
            gridValues[i] = pulledValue
            i++
        }
    }

    fun createGridLine1() {
        // reset values
        gridValues = IntArray(81) { 0 }
        editingList = fullList.toMutableList()

        // take random values from editing list until empty, add to gridvalues as result
        while (editingList.isNotEmpty()) {
            var randomIndex = Random.nextInt(1, editingList.size)
            var pulledValue = editingList[randomIndex]
            editingList.remove(randomIndex)
            var i = 0
            gridValues[i] = pulledValue
            i++
        }
    }
}