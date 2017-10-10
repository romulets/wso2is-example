import React, { Component } from 'react'
import queryString from 'query-string'
import Logout from './Logout'

export default class Validate extends Component {

  constructor (props) {
    super(props)

    this.state = {
      isValid: false,
      accessToken: '',
      userInfo: null
    }

    this.fetchValidateUri = this.fetchValidateUri.bind(this)
    this.getUserInformation = this.getUserInformation.bind(this)
  }

  componentDidMount () {
    const { accessToken } = this.props
    this.setState({ accessToken })
  }

  getUserInformation () {
    const { accessToken } = this.props

    const authPath = window.location.origin + '/wso2Example/api/user-information'
    const query = queryString.stringify({ accessToken })
    const requestUri = `${authPath}?${query}`

    fetch(requestUri)
      .then(response => response.json())
      .then(message => {
        console.log(message)
      })
      .catch(error => {
        console.error(error)
      })
  }

  fetchValidateUri () {
    const { accessToken } = this.props

    const authPath = window.location.origin + '/wso2Example/api/validate-token'
    const query = queryString.stringify({ accessToken })
    const requestUri = `${authPath}?${query}`

    fetch(requestUri)
      .then(response => response.json())
      .then(message => {
        this.setState({ isValid: message.tokenValid })
      })
      .catch(error => {
        console.error(error)
      })
  }

  render () {
    return (
      <div>
        <p>Your token <strong> { this.state.accessToken } </strong></p>

        <p>
          {
            this.state.isValid ?
            'Token is valid' :
            'Your token has not been validated or it has been expired'
          }
        </p>



        <button onClick={this.fetchValidateUri}> Validate </button>

        <br />
        <br />

        <button onClick={this.getUserInformation}> Get user information </button>

        <br />
        <br />

        <Logout />

      </div>
    )
  }
}
