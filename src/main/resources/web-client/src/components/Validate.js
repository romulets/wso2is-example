import React, { Component } from 'react'
import queryString from 'query-string'
import Logout from './Logout'

export default class Validate extends Component {

  constructor (props) {
    super(props)

    this.state = {
      isValid: undefined,
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
      .then(userInfo => {
        this.setState({ userInfo })
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
    const objectInfo = this.state.userInfo
    var userInfo = []

    if (objectInfo) {
      for (var key in objectInfo) {
        if (objectInfo.hasOwnProperty(key)) {
          userInfo.push(
            <tr>
              <td> { key.toUpperCase() } </td>
              <td> { objectInfo[key] } </td>
            </tr>
          )
        }
      }
    }

    return (
      <div>
        <style>{`
          button {
            margin: 5px;
          }

          table {
            margin-bottom: 10px;
          }

          strong.valid {
            background: #00790f;
            color: #fff;
            padding: 5px;
            margin: 10px 0px 10px 5px;
            display: inline-block;
         }

         strong.invalid {
           background: #a50000;
           color: #fff;
           padding: 5px;
           margin: 10px 0px 10px 5px;
           display: inline-block;
        }

        strong.undefined {
          background: #b3a500;
          color: #fff;
          padding: 5px;
          margin: 10px 0px 10px 5px;
          display: inline-block;
        }

        td {
          border-top: 1px solid #ccc;
          border-bottom: 1px solid #ccc;
          padding: 10px;
        }
       `}</style>

        <p>Your access token is <strong> { this.state.accessToken } </strong></p>

        <button onClick={this.fetchValidateUri}> Validate Token </button>

        <button onClick={this.getUserInformation}> Get user information </button>

        <br />

        {
          (() => {
            if (this.state.isValid == undefined) {
              return (
                <strong className='undefined' >The given token was not validated</strong>
              )
            }
            else if (this.state.isValid) {
              return (
                <strong className='valid' >The given token is valid</strong>
              )
            } else {
              return (
                <strong className='invalid' >The given token is invalid</strong>
              )
            }
          })()
        }

        {
          (() => {
            if (userInfo.length > 0) {
              return (
                <table border='1'> { userInfo } </table>
              )
            }
          })()
        }

        <br />

        <Logout />

      </div>
    )
  }
}
