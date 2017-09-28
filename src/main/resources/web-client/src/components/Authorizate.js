import { withCookies } from 'react-cookie'
import React, { Component } from 'react'
import queryString from 'query-string'
import Validate from './Validate'

class Authorizate extends Component {

  constructor (props) {
    super(props)

    this.state = {
      code: '',
      accessToken: ''
    }

    this.fetchAuthUri = this.fetchAuthUri.bind(this)
  }

  componentDidMount () {
    const { code, cookies } = this.props
    const accessToken = cookies.get('accessToken')

    this.setState({ code, accessToken })
  }

  fetchAuthUri (event) {
    const { code } = this.state

    const callbackUri = 'http://localhost:8080/wso2Example'
    const authPath = 'http://localhost:8080/wso2Example/api/authorizate'
    const query = queryString.stringify({
            callbackUri,
            authorizationToken: code
    })

    const requestUri = `${authPath}?${query}`

    fetch(requestUri)
      .then(response => response.json())
      .then(message => {
        const { accessToken } = message
        const { cookies } = this.props

        cookies.set('accessToken', accessToken)
        this.setState({ accessToken })
      })
      .catch(error => {
        console.error(error)
      })
  }

  render () {
    if (!this.state.accessToken || this.state.accessToken.trim().length === 0) {
      return (
        <button onClick={this.fetchAuthUri}> Authorizate </button>
      )
    } else {
      return (
        <Validate accessToken={this.state.accessToken} />
      )
    }
  }
}

export default withCookies(Authorizate)
