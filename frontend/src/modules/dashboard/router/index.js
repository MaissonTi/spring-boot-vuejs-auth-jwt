
const Dashboard = () => import('./../views/Dashboard.vue')

const StudentForm = () => import('./../views/student/StudentForm.vue')
const StudentList = () => import('./../views/student/StudentList.vue')
const Home = () => import('./../views/Home.vue')

export default [
  {
    path: '/',
    component: Dashboard,
    meta: { requiresAuth: true },
    children: [
      {
        path: 'home',
        component: Home,
        meta: { requiresAuth: true },
        alias: ['home', '']
      },
      {
        path: 'student',
        component: StudentList,
        meta: { requiresAuth: true },
        alias: 'studentlist'
      },
      {
        path: 'student/form/:id?',
        component: StudentForm,
        props: true,
        meta: { requiresAuth: true },
        alias: 'studentForm'
      }
    ]
  }
]
