import { shallowMount } from '@vue/test-utils'
import StudentList from './StudentList'
import Vuetify from 'vuetify'
import Vue from 'vue'

Vue.use(Vuetify)

describe('StudentList', () => {
  const wrapper = shallowMount(StudentList)
  const vm = wrapper.vm

  it('should displays students', () => {
    setFakeStudent(wrapper)

    expect(vm.items).toEqual(expect.arrayContaining([{
      id: 1,
      name: 'TesteSudent',
      cpf: '123456789'
    }]))
  })

  it('should not display students', () => {
    resetFakeStudent(wrapper)
    expect(vm.items).toEqual([])
  })

  const setFakeStudent = wrapper => wrapper.setData({
    items: [{
      id: 1,
      name: 'TesteSudent',
      cpf: '123456789'
    }]
  })

  const resetFakeStudent = wrapper => wrapper.setData({
    items: []
  })
})
